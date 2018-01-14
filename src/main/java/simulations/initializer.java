package simulations;


import kademlia.JKademliaNode;
import kademlia.dht.GetParameter;
import kademlia.dht.JKademliaStorageEntry;
import kademlia.exceptions.ContentNotFoundException;
import kademlia.node.KademliaId;
import kademlia.simulations.DHTContentImpl;

import java.io.IOException;

/**
 * @author bipin khatiwada
 * rtrbpn@gmail.com
 * github.com/bipinkh
 */
public class initializer {

    static JKademliaNode kad1, kad2;

    public static void main(String[] args) throws IOException, ContentNotFoundException {

        //Task 1: node initialization

        /***
         * Param 1: The Name of the owner of this instance, can be any name.
         * Param 2: A NodeId for this node
         * Param 3: The port on which this Kademlia instance will run on.
         */

        kad1 = new JKademliaNode("OwnerName1", new KademliaId("ASF45678947584567463"), 12049);
        //another way to initiate node
        //kad2 = new JKademliaNode("OwnerName2", new KademliaId(), 12057);  // Random NodeId will be generated



        //Task 2: Content store

        DHTContentImpl c = new DHTContentImpl(kad1.getOwnerId(), "this is some random shit to store ! ");
        kad1.put(c);    // Put the content on the network



        //Task 3: Content retrieval
        /* Create a GetParameter object with the parameters of the content to retrieve */
        GetParameter gp = new GetParameter(c.getKey(), DHTContentImpl.TYPE);   // Lets look for content by key
        gp.setOwnerId(c.getOwnerId());                    // And content from this owner

        /* Now we call get specifying the GetParameters and the Number of results we want */
        JKademliaStorageEntry content = kad1.get(gp);

        System.out.println("Content Data :: " + new DHTContentImpl().fromSerializedForm(content.getContent()));
        System.out.println("Content Metadata :: " + content.getContentMetadata());


    }


}
