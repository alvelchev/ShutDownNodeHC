
import com.hazelcast.core.*;
import com.hazelcast.config.*;

import java.util.Map;

/**
 *
 * @author alvel
 */
public   class ShutDown  {
    static {
    // ONLY TEMPORARY
    System.setProperty("hazelcast.logging.type", "none");
}
//    public abstract  class ShutDown implements MembershipListener {
    public static void main(String[] args) {
        Config config = new XmlConfigBuilder().build();
        
        HazelcastInstance memberOne = Hazelcast.newHazelcastInstance(config);
         //ADDED TO MEMBER ONE
        memberOne.getCluster().addMembershipListener(new ShutDownMembershipListener());
         //ADDED TO MEMBER TWO
        HazelcastInstance memberTwo = Hazelcast.newHazelcastInstance(config);
        memberTwo.getCluster().addMembershipListener(new ShutDownMembershipListener());
        Map<Integer, String> customerMap = memberOne.getMap("customers");
        customerMap.put(1, "google");
        customerMap.put(2, "apple");
        
        
        
        System.out.println("Hazelcast Nodes in this cluster  " + Hazelcast.getAllHazelcastInstances().size());
        
        memberOne.getCluster().addMembershipListener(new ShutDownMembershipListener());
        memberOne.shutdown();

        System.out.println("Hazelcast Nodes in this cluster After shutdown  " + Hazelcast.getAllHazelcastInstances().size());
        
        
        Map<Integer, String> customerRestored = memberTwo.getMap("customers");
        for (String val : customerRestored.values()) {
            System.out.println("- " + val);
        }

    }

}
