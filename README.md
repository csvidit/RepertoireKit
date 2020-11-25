# RepertoireKit
A CLI Recipe Management System, made using a Java Maven Project.

### Version 0.0.1 Coming Soon (Source code in this repository is not useful at the moment)

This is my first extensive personal project! I would really appreciate any contributions. 

## Highlights
### Export to PDF
The user can instruct the program to export any recipe to PDF. Internally, the recipe file is first converted from an unformatted `.txt` file to a formatted `.md` file, and then it is converted to `.pdf` which is ready for printing. 
### Search Engine
The user can search by recipe name, section name, ingredient name, time limitation, and keywords. 
Search Engine Commands:
- `search ingredient <ingredientName>`
- `search recipe <recipeName>`
- `search section <sectionName>`
- `search for <keywordName>` <br>
### Other inputs
#### Adding a recipe
`add recipe <recipeName>`<br>
After that, the user is prompted to mention the section where they want to store the recipe. A future version will allow unsectioned recipes. <br> After which the user enters the edit wizard as described below.
#### Editing a recipe
After the user opens a recipe, they can enter `edit` to enter the Edit Wizard (if the recipe was just created as described above, writing `edit` is not required), in which they can type the following commands:
- `delete recipe` to delete the recipe. They have to answer a confirmation prompt before the recipe is actually deleted. 
- `add ingredient <ingredientName>` which adds the ingredient if it exists elsewhere in the repertoire, or creates one if it is new. The user then enters the Quantity Edit Wizard, where they can add the quantity of the ingredient for that recipe and the corresponding unit. 
- `edit ingredient <ingredientName>` to change the quantitiy/unit of the ingredient for that recipe. Currently, the system does not support editing the name of the ingredient after it has been added.
- `delete ingredient <ingredientName>` to delete the ingredient from the recipe, but not from the system. If no other recipes reference the ingredient, then it is garbage collected by the JVM at some point (the program does not deal with garbage collection). 
- `add step <stepString>` to add one step at a time. <br>
A future version will allow the addition of multiple ingredients and steps at once. 
#### Export
- After the user opens a recipe, they can type `export` to convert the recipe into a nicely formatted PDF with MarkDown style formatting for printing or other such uses. 
## External Libraries Used
- [Google Guava](https://github.com/google/guava) 
- [Markdown2PDF](https://github.com/Qkyrie/Markdown2Pdf)
