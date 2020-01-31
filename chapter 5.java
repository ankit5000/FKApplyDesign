
interface Preparer {
    public void prepare();
}
class Mechanic implements Preparer {
    @Override
    public void prepare() {
        System.out.println("Mechanic");
    }
}
class Trip {
    Preparer preparer;
    public Trip (Preparer preparer) {
        this.preparer = preparer;
    }
    public void prepare() {
        preparer.prepare();
    }
}
class Driver implements Preparer {
    @Override
    public void prepare() {
        System.out.println("Driver");
    }
}
class TripCoOrdinator implements Preparer {
    @Override
    public void prepare() {
        System.out.println("TripCoOrdinator");
    }
}
public class  Chapter5
{
  public static  void main(String args[])
  {

  }
}

