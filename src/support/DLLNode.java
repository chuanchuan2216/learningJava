package support;

public class DLLNode<T> {

    private T info;
    private DLLNode<T> forword, back;

    public DLLNode(T info) {
        this.info = info;
        this.forword = null;
        this.back = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public DLLNode<T> getForword() {
        return forword;
    }

    public void setForword(DLLNode<T> forword) {
        this.forword = forword;
    }

    public DLLNode<T> getBack() {
        return back;
    }

    public void setBack(DLLNode<T> back) {
        this.back = back;
    }

}
