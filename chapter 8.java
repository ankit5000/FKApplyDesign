
import java.util.ArrayList;
//Chapter 8
class Bicycle {
    char  size;
    Parts parts;

    public Bicycle(char size, Parts parts)
    {
        this.parts=parts;
        this.size=size;

    }
    public void spares()
    {
        ArrayList<Part> a = parts.spares();
        for(Part p:a)
        {
            System.out.println(p.name+" "+p.description);
        }

    }

}

class Part {
    String name, description;
    boolean needs_spare=true;
    public Part(String name,String description,boolean needs_spare) {
        this.name=name;
        this.description=description;
        this.needs_spare=needs_spare;

    }


}
class Parts {
    Part part;
    ArrayList<Part> a;
    public Parts(Part ...part)
    {
        a = new ArrayList<Part>();
        for(Part p:part)
            a.add(p);
    }
    public ArrayList<Part> spares()
    {
        ArrayList<Part> a1 = new ArrayList<Part>();
        for(Part p: a)
        {
            if(p!=null&&p.needs_spare)
                a1.add(p);
        }
        return a1;
    }
}

public class Chapter8 {

    public static void main(String[] args) {
        Part chain = new Part("chain","10-speed",true);
        Part  road_tire  = new Part("tire_size",  "23",true);
        Part tape = new Part("tape_color", "red",true);
        Part mountain_tire = new Part("tire_size", "2.1",true);
        Part rear_shock =new Part("rear_shock", "Fox",true);
        Part front_shock = new Part( "front_shock", "Manitou", false);
        Parts road_bike_part = new Parts(chain,road_tire,tape);
        Bicycle road_bike = new Bicycle('L',road_bike_part);
        Bicycle mountain_bike = new Bicycle( 'L' ,   new Parts(chain, mountain_tire,
                front_shock, rear_shock));
        System.out.println(road_bike.size);
        road_bike.spares();
        System.out.println(mountain_bike.size);
        mountain_bike.spares();

    }
}
