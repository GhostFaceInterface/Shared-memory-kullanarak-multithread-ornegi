public class Main {


    public static void main(String[] args) {
        SharedMemory sharedMemory = new SharedMemory();
        Thread thread1 = new Thread(new veriOkuma(sharedMemory));
        Thread thread2 = new Thread(new NotHesaplama(sharedMemory));
        Thread thread3 = new Thread(new rapor(sharedMemory));


        thread1.start();
        thread2.start();
        thread3.start();
    
    }

//Shared memory --------------------------------------    
    static class  SharedMemory {
    private int data;
        
    public synchronized int readData(){
        return data;
    }

    public synchronized void writeData(int data){
        this.data= data;
    }

    }


//veri okuma --------------------------------------
    static class veriOkuma implements Runnable{
        private SharedMemory sharedMemory;
        public veriOkuma(SharedMemory sharedMemory){
            this.sharedMemory = sharedMemory;
        }
        @Override 
        public void run(){
            int data = sharedMemory.readData();
            System.out.println("Veri Okuma: " + data);
        }

    }
//hesaplama -------------------------------------- 

static class NotHesaplama implements Runnable{
    private SharedMemory sharedMemory;
    

    public NotHesaplama(SharedMemory sharedMemory){
        this.sharedMemory = sharedMemory;
    }

    @Override 
    public void run(){
        int data = sharedMemory.readData();
        int note = data*10;
        sharedMemory.writeData(note);
        System.out.println("Not Hesaplama: " + note);
    }

}
//rapor sunma -------------------------------------- 
    static class rapor implements Runnable {
        private SharedMemory sharedMemory;

        public rapor(SharedMemory sharedMemory) {
            this.sharedMemory = sharedMemory;
        }
        
        @Override 
        public void run(){
            if(sharedMemory.data >50)
            System.out.println("öğrenci başarılı");
            else 
            System.out.println("öğrenci MAL :D");

        }
    }



  
    
}
