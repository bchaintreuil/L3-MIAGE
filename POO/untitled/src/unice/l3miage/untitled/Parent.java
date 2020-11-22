package unice.l3miage.untitled;

public abstract class Parent {
    public Parent() {
        System.out.println(this.getClass().getSimpleName().replace("Builder", "").toLowerCase());
    }
}
