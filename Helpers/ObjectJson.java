package Helpers;
import com.google.gson.Gson;
import Products.Product;
import java.io.*;
/**
 * The ObjectJson class provides methods for serializing and deserializing objects to and from JSON format.
 * It uses the Gson library to perform the serialization and deserialization.
 */
public class ObjectJson {
    /**
     * A static method that serializes an object to a JSON file using the JsonIdentifiable interface to get the file name from the object.
     * The file is created in a subdirectory 'JSON Files' within the package directory of the object's class.
     *
     * @param object The object to be serialized to JSON.
     **/
    public static void objectToJson(Object object) {
        Gson gson = new Gson();
        // Call the getFileInPackageDir method to get the JSON file.
        File file = getFileInPackageDir(object);
        // Write the object to the file as JSON using Gson.
        try {
            assert file != null;
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(object, writer);
            }
        } catch (Exception e) {
            System.err.println("Error writing object to JSON file: " + e.getMessage());
        }
    }


    public static File getFileInPackageDir(Object object) {
        // Get the class of the object
        Class<?> contextClass = object.getClass();
        //check if the object implements the JsonIdentifiable interface by casting it to the interface
        if (!(object instanceof JsonIdentifiable)) {
            System.err.println("This object does not implement JsonIdentifiable interface.");
        }
        // Get the package directory of the object's class
        String packageDir = System.getProperty("user.dir") + "\\" + contextClass.getPackageName() + "\\JSON Files\\";
        // Create a file path object for the package directory
        try {
            assert object instanceof JsonIdentifiable;
            return getFile((JsonIdentifiable) object, packageDir);
        }catch (Exception e){
            System.err.println("This object does not implement JsonIdentifiable interface." + e);
        }
        return null;
    }

    /**
     * Helper method to get or create the JSON file for an object.
     * Attempts to load the package directory of the object's class, if it doesn't exist, it will be created.
     * Attempts to find the JSON file in the package directory, if it doesn't exist, a blank file will be created.
     * The file name is defined by the JsonIdentifiable interface implemented by the object.
     *
     * @param object The object to be serialized to JSON.
     * @param packageDir The package directory of the object's class.
     * @return The JSON file.
     */
    private static File getFile(JsonIdentifiable object, String packageDir) {
        File packagePath = new File(packageDir);
        // Check if the directory exists, create it if not.
        if (!packagePath.exists()) {
            // Attempt to create the JSON Files directory
            //noinspection ResultOfMethodCallIgnored
            packagePath.mkdirs();
        }
        //Create a file path object by casting the passed object to the JsonIdentifiable interface
        // and use the getJsonId method to get the file name.
        return new File(packagePath, (object.getJsonId() + ".json"));
    }

    /**
     * Deserializes a JSON file to an object.
     * Attempts to find the passed json file name in the package directory of the passed class.
     * @param fileName The name of the JSON file to be deserialized, this is defined by the JsonIdentifiable interface implemented by the object.
     * @param contextClass The class of the object to be deserialized.
     * @return The deserialized object.
     * @param <T> The type of the object to be deserialized. (This is automatically inferred from the contextClass parameter)
     */
    public static <T> T objectFromJson(String fileName, Class<T> contextClass)  {
        Gson gson = new Gson();

        // Construct the directory path where the JSON file is located.
        String packageDir = System.getProperty("user.dir") + "\\" + contextClass.getPackageName() + "\\JSON Files\\";

        // Remove the file extension if it exists in the fileName.
        if (fileName.contains(".")) {
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
        }

        // Construct the full path to the JSON file.
        File file = new File(packageDir + fileName + ".json");

        // Use FileReader to read the JSON file and deserialize it into an object of type T.
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, contextClass);
        } catch (IOException e) {
            // Throw an IOException with a custom message if an error occurs during reading.
            System.err.println("The file could not be found. " + e);
        }
        return null;
    }

    /**
     * Retrieves an array of File objects representing all the files in the directory associated with a given class.
     *
     * @param passedClass The class whose associated directory's files are to be listed.
     * @return An array of File objects, or null if the directory does not exist, is not a directory, or is empty.
     */
    public static String getClassDir(Class<?> passedClass) throws ClassCastException {
        //check if the class implements the JsonIdentifiable interface
        if (!JsonIdentifiable.class.isAssignableFrom(passedClass)){
           throw new ClassCastException("Object does not implement JsonIdentifiable");
        }
        //Return the full package directory as a File object with the users current directory
        // and the package directory of the passed class.
        return System.getProperty("user.dir") + "\\" + passedClass.getPackageName() + "\\JSON Files\\";
    }

    /**
     * Retrieves an array of File objects representing all the files in the directory associated with a given class.
     *
     * @param passedClass The class whose associated directory's files are to be listed.
     * @return An array of File objects, or null if the directory does not exist, is not a directory, or is empty.
     */
    public static File[] listFiles(Class<?> passedClass) {
        String packageDirPath = ObjectJson.getClassDir(passedClass);
        File packageDir = new File(packageDirPath);

        // Check if the directory exists and is a directory
        if (packageDir.exists() && packageDir.isDirectory()) {
            // Return the list of files in the directory
            return packageDir.listFiles();
        } else {
            // Return null if the directory does not exist or is not a directory
            return null;
        }
    }

    /**
     * Searches for an object with a given JSON ID in the directory associated with a given class.
     *
     * @param jsonId The JSON ID of the object to search for.
     * @param contextClass The class of the object to search for.
     * @return The object if found, or null if not found.
     */
    public static Object searchObject(String jsonId, Class<?> contextClass) {
        File[] files = ObjectJson.listFiles(contextClass);
        if (files != null) {
            for (File file : files) {
                JsonIdentifiable object = (JsonIdentifiable) ObjectJson.objectFromJson(file.getName(), contextClass);
                //If an object file is found
                if (object != null) {
                    //Check if the ID matches the user entered ID
                    if (object.getJsonId().equals(jsonId)) {
                        return object;
                    }
                } else {
                    //if product = null
                    System.out.println("No matching product was found. Please try again");
                }
            }
        } else {
            //If no files are found in Product/Json Files/
            System.out.println("Error loading files from directory. Please try again.");
        }
        return null;
    }

    /**
     * Deletes an object with a given JSON ID from the directory associated with a given class.
     *
     * @param jsonId The JSON ID of the object to delete.
     * @param contextClass The class of the object to delete.
     */
    public static void deleteObject(String jsonId, Class<?> contextClass) {
        File[] files = ObjectJson.listFiles(contextClass);
        if (files != null) {
            for (File file : files) {
                JsonIdentifiable object = (JsonIdentifiable) ObjectJson.objectFromJson(file.getName(), contextClass);
                //If an object file is found
                if (object != null) {
                    //Check if the ID matches the user entered ID
                    if (object.getJsonId().equals(jsonId)) {
                        file.delete();
                    }
                } else {
                    //if product = null
                    System.out.println("No matching product was found. Please try again");
                }
            }
        } else {
            //If no files are found in Product/Json Files/
            System.out.println("Error loading files from directory. Please try again.");
        }
    }


    /**
     * Example usage of objectToJson and objectFromJson
     * This example creates a Product object, writes it to a JSON file, then recalls the object from the JSON file.
     * The jsonId is defined by the JsonIdentifiable interface implemented by the object.
     *--
     * @param args Unused
     */
    public static void main(String[] args) {
        // Example usage of objectToJson to write a Product object to a JSON file.
        Product product = new Product("Milk", 2.99, "Whole milk", "P001");
        objectToJson(product);
        System.out.println("Product written to JSON file");
        // Example usage of objectFromJson to recreate a Product object from a JSON file.
        String jsonId = "Milk_P001";
        Product productFromJson;
        productFromJson = objectFromJson(jsonId, Product.class);
        System.out.println("Product successfully recreated from JSON file");
        System.out.println("Product: " + productFromJson);
    }
}


