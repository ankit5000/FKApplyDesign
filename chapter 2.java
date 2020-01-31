
class Gear {
    int chainring, cog;
    Wheel wheel;

    public Gear(int chainring, int cog, Wheel wheel) {
        this.chainring = chainring;
        this.cog = cog;
        this.wheel = wheel;

    }

    public double ratio() {
        return chainring / cog;
    }

    public double gear_inches() {
        return ratio() * wheel.diameter();
    }
}
    class Wheel {
        int rim;
        double tire;

        public Wheel(int rim,double tire)
        {
            this.rim=rim;
            this.tire=tire;
        }

        public double  diameter()

        {
            return  rim + (tire * 2);
        }

        public double circumference()
        {
            return diameter() * 3.1416;
        }}





public class Chapter2 {
    public static void main(String args[])
    {
        Wheel wheel= new Wheel(26, 1.5) ;
        Gear gear = new Gear(52,11,wheel);
        System.out.println(gear.ratio()+","+gear.gear_inches());
        System.out.println( wheel.diameter()+" ,"+
                wheel.circumference());

    }
}
