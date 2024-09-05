package funBook;
import java.util.*;

public class funBook {
    private static HashMap<String,List<String>> map;
    public funBook()
    {
        map= new HashMap<String,List<String>>();
    }

    //add User

    public void addUser(String user)
    {
        if(!map.containsKey(user))
        {
            map.put(user, new ArrayList<>());
        }
    }

    //add Friend

    public void addFriend(String user, String Friend)
    {
        addUser(user);
        addUser(Friend);

        map.get(user).add(Friend);
        map.get(Friend).add(user);
    }

    //show connections

    public List<String> showConnections(String name)
    {
        return map.getOrDefault(name,Collections.emptyList());
    }

    // Friend Suggestions

    public List<String> suggestions(String name)
    {
        if(!map.containsKey(name))
        {
            return Collections.emptyList();
        }
        List<String> friendList = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer(name);
        visited.add(name);

        while(!q.isEmpty())
        {
            String curr = q.poll();
            for(String friend :map.get(curr))
            {
                if(!visited.contains(friend))
                {
                    visited.add(friend);
                    q.offer(friend);

                    if(!map.get(name).contains(friend))
                    {
                        friendList.add(friend);
                    }
                }
            }
        }
        return friendList;
    }


public static void main (String[] args)
{
    funBook abc = new funBook();
    abc.addFriend("shashank", "deepak");
    abc.addFriend("deepak", "arsh");
    abc.addFriend("shashank", "mohit");
    abc.addFriend("mohit", "vishal");
    abc.addFriend("vishal", "chandel");

    System.out.println("Shashank friendList is :");
    List<String> friendSuggest = new ArrayList<>();
    friendSuggest = abc.suggestions("shashank");
    System.out.println(friendSuggest);
    friendSuggest = abc.suggestions("mohit");
    System.out.println(friendSuggest);
    
    // OUTPUT will be -- 
    
    //[arsh, vishal, chandel]
    //[deepak, chandel, arsh]
   
}
}

