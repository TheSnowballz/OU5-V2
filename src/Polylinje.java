public class Polylinje
{
    private Punkt[] horn;
    private String farg = "svart";
    private int bredd = 1;
    public Polylinje ()
    {
        this.horn = new Punkt[0];
    }
    public Polylinje (Punkt[] horn)
    {
        this.horn = new Punkt[horn.length];
        for (int i = 0; i < horn.length; i++)
            this.horn[i] = new Punkt (horn[i]);
    }
    public String toString () {
        StringBuilder sb = new StringBuilder("{[ ");
        for(int i = 0; i < this.horn.length; i++){
            sb.append(this.horn[i]);
        }
        sb.append("], " + getFarg() + ", " + getBredd() + " ]}");

        return sb.toString();
    }
    public Punkt[] getHorn () {

        Punkt[] h = new Punkt[this.horn.length];
        for (int i = 0; i < this.horn.length; i++){
            h[i] = this.horn[i];
        }
        return h;
    }
    public String getFarg () {
        return this.farg;
    }
    public int getBredd () {
        return this.bredd;
    }
    public void setFarg (String farg) {
        this.farg = farg;
    }
    public void setBredd (int bredd) {
        this.bredd = bredd;
    }
    public double langd () {
        double langd = 0;
        for (int i = 0; i < this.horn.length-1; i++){
            langd += this.horn[i].avstand(this.horn[i+1]);
        }
        return langd;
    }
    public void laggTill (Punkt horn)
    {
        Punkt[] h = new Punkt[this.horn.length + 1];
        int i = 0;
        for (i = 0; i < this.horn.length; i++)
            h[i] = this.horn[i];
        h[i] = new Punkt (horn);
        this.horn = h;
    }
    public void laggTillFramfor (Punkt horn, String hornNamn) {
        int i = 0;
        Punkt[] h = new Punkt[this.horn.length + 1];
        for (; i < this.horn.length; i++){
            if(!this.horn[i].namn.equals(hornNamn)) {
                h[i] = this.horn[i];
            }
            else {
                h[i] = horn;
                h[i + 1] = this.horn[i];
                break;
            }
        }
        for (; i < this.horn.length; i++){
            h[i + 1] = this.horn[i];
        }
        this.horn = h;
    }
    public void taBort (String hornNamn) {
        int j = 0;
        Punkt[] h = new Punkt[this.horn.length-1];
        for(int i = 0; i < this.horn.length; i++){
            if(!this.horn[i].namn.equals(hornNamn)){
                h[j] = this.horn[i];
                j++;
            }
        }
        this.horn = h;
    }
    public class PolylinjeIterator
    {
        private int aktuell = -1;
        public PolylinjeIterator ()
        {
            if (Polylinje.this.horn.length > 0)
                aktuell = 0;
        }
        public boolean finnsHorn ()
        {
            return aktuell != -1;
        }
        public Punkt horn () throws java.util.NoSuchElementException
        {
            if (!this.finnsHorn ())
                throw new java.util.NoSuchElementException (
                        "slut av iterationen");
            Punkt horn = Polylinje.this.horn[aktuell];
            return horn;
        }
        public void gaFram ()
        {
            if (aktuell >= 0 &&
                    aktuell < Polylinje.this.horn.length - 1)
                aktuell++;
            else
                aktuell = -1;
        }
    }

}