import java.util.Scanner;

public class Main {
    public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TaskManager tasksManager = new TaskManager();
        Task t1 = tasksManager.addTask("Переезд", "Описание переезда", "NEW");

        while(true){
            menu();
            int input = scanner.nextInt();
            if(input==1){

            }else if(input==2){

            }else if(input==3){

            }else if(input==4){

            }else if(input==5){

            }
        }
    }

    public void menu(){
        System.out.println("1. Создать задачу");
        System.out.println("2. Создать подзадачу");
        System.out.println("3. Изменить статус задачи/подзадачи");
        System.out.println("4. Удалить задачу/подзадачу");
        System.out.println("5. Выход");
    }
}
