# jasper-dynamic-sheets
A sample project which demonstrates how to add dynamic sheets/pages to a Jasper Report

This project also demonstrates on how to generate a Jasper report from below formats.

* Excel (.xlsx)
* PDF (.pdf)
* MS Word (.docx)
* MS PowerPoint (.pptx)
* CSV (.csv)

Refer below posts for detailed explanation about the source code.
* [JasperReports in Java - Tips for exporting to different file formats](https://mytechnicaldocs.blogspot.sg/2018/01/jasperreports-in-java-tips-for.html)
* [JasperReports in Java - Add sheets dynamically](https://mytechnicaldocs.blogspot.sg/2018/01/jasperreports-in-java-add-sheets.html)

## Build & Run The Example

After cloning the project please follow below instructions to try the example.

#### Command-Line Instructions

* **Prerequisites:**
    * Install the latest version of [Java](https://java.com/) and [Maven](https://maven.apache.org/download.html). (Requires Java 1.8 or above)
    * You may need to set your `JAVA_HOME`.

```bash
cd jasper-dynamic-sheets
# Compile and run
mvn clean compile
mvn exec:java
```
After executing above commands without eny errors, you can find the generated reports files under project's base directory with file name in the format of ```My_Report_<YYYY-MM-DD>.<extension>```
