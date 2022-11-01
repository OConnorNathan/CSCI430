package UserInterface;

public abstract class WhState {
    protected static WhContext context;
    protected WhState() {
      //context = WhContext.instance();
    }
    public abstract void run();
}