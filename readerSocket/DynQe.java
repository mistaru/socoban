public class DynQoue {
    int[] qoue;
    int[] buffer;
    int teil;
    public DynQoue() {
        qoue = new int[2];
        teil = qoue.length - 1;
    }
    
    public void put(int element){
        if(teil == 0){
            buffer = qoue;
            qoue = new int[buffer.length * 2 - 1];
            for(int i = 0; i < buffer.length;i++)
                qoue[i+buffer.length - 1] = buffer[i];
            teil = buffer.length - 1;
            buffer = null;
        }
        qoue[teil] = element;
        teil--;
    }
    public int get(){
        if(teil == qoue.length -1){
            System.out.println("Очередь пуста");
            return 0;
            }
        int element = qoue[qoue.length - 1];
        put(element);
        for(int i = 0; i < (qoue.length - (teil + 1)); i++){
            qoue[qoue.length - i -1] = qoue[qoue.length - i - 2];
        }
        teil++;
        return element;
    }
    public void print(){
        for(int i = teil + 1; i < qoue.length; i++)
            System.out.print(qoue[i] + " ");
            System.out.println();
    }
}