# this script is used to delete all .class files in the given directory
import os

def clear_class_files(directory):
    if not os.path.exists(directory):
        print(f"Directory {directory} does not exist.")
        return
    for filename in os.listdir(directory):
        if filename.endswith(".class"):
            file_path = os.path.join(directory, filename)
            os.remove(file_path)

# Example usage
# add your own directory path
clear_class_files('d:/Coding/OOP/OOP-Project/src')