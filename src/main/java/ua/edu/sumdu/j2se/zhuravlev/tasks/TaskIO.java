package ua.edu.sumdu.j2se.zhuravlev.tasks;

import com.google.gson.Gson;

import java.io.*;
import java.nio.CharBuffer;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        DataOutputStream output = new DataOutputStream(out);
        output.writeInt(tasks.size());
        for(Task task: tasks){
            output.writeInt(task.getTitle().length());
            output.writeUTF(task.getTitle());
            output.writeInt(task.isActive() ? 1:0);
            output.writeInt(task.getRepeatInterval());
            if (task.isRepeated()){
                output.writeLong(task.getStartTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                output.writeLong(task.getEndTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            } else {
                output.writeLong(task.getTime().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
            }
        }
    }
    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        DataInputStream input = new DataInputStream(in);
        int size = input.readInt();
        for (int i = 0; i < size; i++){
            Task temp;
            int length = input.readInt();
            String title = input.readUTF();
            boolean active = (input.readInt() == 1);
            int repeatInterval = input.readInt();
            if (repeatInterval > 0){
                long start = input.readLong();
                long end = input.readLong();
                LocalDateTime startTime = Instant.ofEpochMilli(start).atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime endTime = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDateTime();
                temp = new Task(title,startTime,endTime,repeatInterval);
            } else {
                long timeTemp = input.readLong();
                LocalDateTime time = Instant.ofEpochMilli(timeTemp).atZone(ZoneId.systemDefault()).toLocalDateTime();
                temp = new Task(title,time);
            }
            temp.setActive(active);
            tasks.add(temp);
        }
    }
    public static void writeBinary(AbstractTaskList tasks, File file){
        try(OutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            write(tasks,outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file){
        try(InputStream input = new ObjectInputStream(new FileInputStream(file))) {
            read(tasks,input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        Task[] tasksTemp = new Task[tasks.size()];
        for (int i = 0; i<tasks.size(); i++){
            tasksTemp[i]=tasks.getTask(i);
        }
        Gson gson = new Gson();
        gson.toJson(tasksTemp,out);
        out.flush();
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        Gson gson = new Gson();
        Task[] tasksTemp = gson.fromJson(in, Task[].class);
        for (Task task: tasksTemp) {
            tasks.add(task);
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        try(Writer output = new BufferedWriter(new FileWriter(file))) {
            write(tasks,output);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        try(Reader input = new BufferedReader(new FileReader(file))) {
            read(tasks,input);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
