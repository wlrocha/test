== Requirements ==

1) Eclipse 3.6 or better with Java EE support installed is recomended, but you're free to use any other Java IDE or text editor.
2) Maven 3.0 or better, or m2eclipse elipse plugin.
3) MySQL (with empty root password).

== Usage ==

Once you have met the requirements, the test can be run opening a command prompt, changing to the folder on which the test was decompressed, and invoking the following command:

$ mvn jetty:run

This procedure will take some time the first time you try it, as it has to download all the project's dependencies from the maven repositories.

Alternatively, if you have the m2eclipse plugin installed on your Eclipse installation, right-click on the project's node on the Project Explorer and select Run As -> Maven build..., and enter "jetty:run" (without the quotes) on the Goals field of the Edit Configuration dialog.

== Test ==

1) Complete the org.appfuse.model.Robot bean with the following attributes:

   a) id : Long
   b) designation : String
   c) dateOfBuild : Date
   d) qualityCheckPassed : Boolean
   e) owner : User (org.appfuse.model.User)

   Add all the necessary getters and setters.

2) Generate a hibernate mapping for the Robot bean, and declare it as a
resource in the proper context definition.

3) Add all the necessary components (DAO, Manager, Controllers, etc.) to list
the records in the table associated with the Robot entity, and modify or delete
existing instances. Use the existing functionality as a guideline.
