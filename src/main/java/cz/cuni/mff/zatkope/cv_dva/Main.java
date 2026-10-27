package cz.cuni.mff.zatkope.cv_dva;

interface Printer {
    void print(String msg);
}

class GenericPrinter implements Printer {
    public GenericPrinter(){
        print(this.toString());
    }

    public void print(String msg) {
        System.out.print(msg);
    }
}

class TimestampPrinter extends GenericPrinter {
    public TimestampPrinter(){
        super.print(this.toString());
    }

    public void print(String msg) {
        System.out.print(new java.util.Date());
    }
}

class LabelPrinter extends GenericPrinter {
    String label;

    public LabelPrinter(String label){
        super.print(this.toString());

        this.label = label;
    }

    public void print (String msg) {
        super.print(label + " " + msg);
    }
}

class NullPrinter extends GenericPrinter {}

class Logger {
    private GenericPrinter printer = new NullPrinter();
    private int level = 0;

    public void addPrinter(GenericPrinter printer){
        this.printer = printer;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void log(int level, String msg){
        if(level < this.level) return;

        printer.print(msg);
    }
}

public class Main {
    public static void main(String[] args){
        Printer printer = new TimestampPrinter();
        Logger logger = new Logger();
        logger.log(2, "Tohle je zprava");
    }
}
