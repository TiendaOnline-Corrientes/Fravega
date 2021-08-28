package Model;


public class RequestBuilderPatron {

    private final Request request;

// esta clase nos ayuda a crear de fomar mas facil nuestra clase
    public RequestBuilderPatron() {
        this.request = new Request();
    }

    public static RequestBuilderPatron setUser(){
        return new RequestBuilderPatron();
    }

    public RequestBuilderPatron setName(String name){
        this.request.setName(name);
        return this;
    }

    public RequestBuilderPatron setJob(String job){
        this.request.setJob(job);
        return this;
    }

    public Request build(){
        return this.request;
    }
}
