
class Gear{
    int chainring, cog;

    public Gear(int chainring, int cog)
    {
        this.chainring=chainring;
        this.cog=cog;

    }
    public double ratio() {
        return   chainring / cog;
    }
    public double gear_inches(double diameter) {
        return   ratio() *diameter;
    }

}
class Wheel
{
    int rim;
    double tire;
    Gear gear;
    public Wheel(int rim,double tire,int chainring, int cog)
    {
        this.rim=rim;
        this.tire=tire;
        gear = new Gear(chainring,cog);
    }

    public double  diameter()

    {
        return  rim + (tire * 2);
    }

    public double gear_inches()
    {
        return gear.gear_inches(diameter());

    }
}

public class Chapter3 {
    public static void main(String args[])
    {
        Wheel wheel= new Wheel(26, 1.5, 52, 11) ;
        System.out.println(wheel.gear_inches());


    }
}

/**/

