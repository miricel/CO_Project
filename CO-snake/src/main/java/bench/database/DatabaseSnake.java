package bench.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DatabaseSnake {
	
	private ArrayList<Data> data = new ArrayList<Data>();
	private static DatabaseSnake database = null;
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static DatabaseSnake getInstance()
    {
        if (database == null)
        	database = new DatabaseSnake();
        return database;
    }

	public ArrayList<Data> getData() {
		return data;
	}

	public void setData(ArrayList<Data> data) {
		this.data = data;
	}

	public void readFromFile()
    {
        try
        {
            Type userListType = new TypeToken<ArrayList<Data>>(){}.getType();
            FileReader fr = new FileReader("SnakeDatabase.json");
            BufferedReader reader = new BufferedReader(fr);
            if (new File("SnakeDatabase.json").length() > 0)
                data = gson.fromJson(reader, userListType);
            reader.close();
        }
        catch (IOException e)
        {
            System.err.println("An IOException was caught!" + e.getMessage());
        }
    }

    public void writeToFile()
    {
        try
        {
            FileWriter fw = new FileWriter("SnakeDatabase.json");
            BufferedWriter writer = new BufferedWriter(fw);
            gson.toJson(data, writer);
            writer.close();
        }
        catch (IOException e)
        {
            System.err.println("An IOException was caught!" + e.getMessage());
        }
    }
}
