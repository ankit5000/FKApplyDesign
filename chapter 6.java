public class Bicycle {

    int size,tire_size;
    String chain;

    public void spares(){
        tire_size = tire_size;
    }

    public String default_chain(){
        return "10-speed";
    }

    public int getTire_size(){
        return 0;
    }

}

class RoadBike extends Bicycle{

    String tape_color;

    public int getTire_size(){
        return 23;
    }

}

class MountainBike extends Bicycle{

    String front_shock,rear_shock;

    public int getTire_size(){
        return 21;
    }

}
