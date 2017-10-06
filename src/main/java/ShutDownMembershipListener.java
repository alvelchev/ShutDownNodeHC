
import com.hazelcast.core.MemberAttributeEvent;
import com.hazelcast.core.MembershipEvent;
import com.hazelcast.core.MembershipListener;


/**         
 *
 * @author alvel
 */
public  class ShutDownMembershipListener implements MembershipListener {

    public ShutDownMembershipListener() {
             
    }
    
    @Override
    public void memberAdded(MembershipEvent membershipEvent) {
         System.err.println("Added: " + membershipEvent.toString());
    }

    @Override
    public void memberRemoved(MembershipEvent membershipEvent) {
         System.err.println("Removed: " + membershipEvent.toString());
    }

    @Override
    public void memberAttributeChanged(MemberAttributeEvent memberAttributeEvent) {
        System.err.println("Member attribute changed: " + memberAttributeEvent.toString());
    }
    
}
