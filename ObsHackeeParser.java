package classes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ObsHackeeParser {

    static Path logPath = Paths.get("/home/pros/.0dev/Scripts/obshackee/output.log");

//    static class FullFile
//    static class FullFile
////    class FullFile
//    {
//        String filePath;
//        String fileContent;
//    }

    public static void main(String[] args)
    {
        ObsHackeeParser xx = new ObsHackeeParser();
//        FullFile ff = xx.new FullFile();
        File file = new File("/home/pros/.0dev/Scripts/obshackee/error.log");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PrintStream ps = new PrintStream(fos);
        System.setErr(ps);

        try {
            Files.deleteIfExists(logPath);
        } catch (IOException e) {
            log("F 1");
            throw new RuntimeException(e);
        }

        try {
            Files.write(logPath, "\n".getBytes(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {
            log("F2");
            throw new RuntimeException(e);
        }

        //============================= NOW I WRITE MY CODE!!!!!

        Path path = Paths.get(args[0]); //it should be path to obsFile, where the text is
        String fileContent = "";
        List<String> lines = null;
        try {
            fileContent = Files.readString(path);
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //the line which marks as beginning of the code snippet: **Start example 1**
        var p = Pattern.compile("\\*\\*Example \\d+ start\\*\\*");  //THIS WORKS, there are two!!!



//        log("ZUZA:::::::");
//        log(fileContent);
        var match = p.matcher(fileContent);
//        log("END=======\n");
        long count = match.results().count(); //this works great!!!
//        log(String.valueOf(count));
//        if (Integer.valueOf(args[1]) > count)   //args[1] is the choice of the user
//        {
//            throw new RuntimeException("Somethign is not right!. args[1] is '" + args[1] + "', and count is: " + count);
//        }

//        int args1 = 1;
        int args1 = Integer.valueOf(args[1]);   //args[1] is the user's choice example
        int ex1Start = Integer.MIN_VALUE;
        for (int i = 0; i < lines.size(); i++)
        {
            String intermStr = lines.get(i);
            if (intermStr.startsWith("**Start example " + args[1] + "**"))  //this is the pattern of start
            {
                ex1Start = i;
            }
        }
//        log("WTF: " + String.valueOf(ex1Start));
        //now find end:
        int ex1End = Integer.MIN_VALUE;
        for (int i = 0; i < lines.size(); i++)
        {
            String intermStr = lines.get(i);
            if (intermStr.startsWith("**End example " + args[1] + "**"))  //this is the pattern of finish
            {
                ex1End = i;
            }
        }
//        log(String.valueOf(ex1End));

//        log("=STOP=");
//        log(lines.get(ex1Start));

        var myRangeOfLines = lines.subList(ex1Start, ex1End + 1);   //from start to end including, this is for EXAMPLE_1

        //=======
        var resFiles = new ArrayList<FullFile>();
        recAdd(resFiles, myRangeOfLines);
//        log("+++++++++++++++++++++++");
        resFiles.forEach(f ->
        {
            log(f.filePath);
            log(f.fileContent);
        });
//        log("OUTOUTOUT||||||||||");

        //return;
        ///home/pros/.1_Programming/JavaProjectTemplate/app/
        String sp = "/home/pros/.1_Programming/JavaProjectTemplate/app/";
        resFiles.forEach(f -> {
            Path pppp = Paths.get(sp + f.filePath);
            try {
                Files.write(pppp, f.fileContent.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        //===== below worked fine!!
        if (5> 3 )
            return;
//        var resFiles = new ArrayList<FullFile>();
        for (int i = 0; i < myRangeOfLines.size(); i++)
        {
            var line = myRangeOfLines.get(i);
            var px = Pattern.compile("\\*\\*`.*`\\*\\*");
            var matchx = px.matcher(line);
            if (matchx.matches())
            {
                log("SUPER BOOM. " + i + "");
                //it means we are at file Title
                String titlePath = line.substring(line.indexOf('`') + 1, line.lastIndexOf('`'));
                var f = new FullFile();
                f.filePath = titlePath;

                for (int j = i + 2; j < myRangeOfLines.size(); j++)
                {
                    var ll = myRangeOfLines.get(j);
                    var ppx = Pattern.compile("```");
                    var mmm = ppx.matcher(ll);
                    if (mmm.matches())
                    {
                        log("IT'S MATCH, ind: " + j);
                        var fileContentLines = myRangeOfLines.subList(i + 2, j - 1);
//                        var fc = join(fileContentLines, '\n');
//                        fileContentLines.stream().collect(String.join("\n"))
                        var fc = String.join("\n", fileContentLines);


                        f.fileContent = fc;
                        log("================================");
                        log(f.filePath);
                        log(f.fileContent);
//                        return;
                    }
                }
            }

        }






            //THIS IS OLDER CODE, I con't touch below, it was PoC

        for (int i = 0; i < myRangeOfLines.size(); i++)
        {
            var px = Pattern.compile("\\*\\*`.*`\\*\\*");
            var matchx = px.matcher(myRangeOfLines.get(i));
            if (matchx.matches())
            {
//                log("SUPER BOOM. " + i + "");
            }
            long countx = match.results().count();
//            log("COunt: " + countx + ". Line itself: " + myRangeOfLines.get(i));

//            var ss = matchx.matches();
//            if (ss)
//            {
//                log("SO, my conc is: " + ss);
//                log("RES: " + i);
//            }

        }
        String sd = "**`src/main/java/head/TreeMain.java`**";
        var px = Pattern.compile("\\*\\*`.*`\\*\\*");
        var mx = px.matcher(sd);
//        log("SEC: " + mx.matches());








//        2:45 to 3:45
//            6:45 to 7:45


//        match.results().map(e -> e.).forEach(ProdParser::log);

//        boolean matches = match.matches();
//        log(String.valueOf(matches));
//        log("---");
//        log(match.group(1));



        //next stpes
    }

    static void recAdd(List<FullFile> fullFileList, List<String> fileContentSnippetLines)
    {
//        if (startInd == -1 || endInd == -1)
//        {
//            return;
//        }
        outer:
        for (int i = 0; i < fileContentSnippetLines.size(); i++)
        {
            var line = fileContentSnippetLines.get(i);
            var px = Pattern.compile("\\*\\*`.*`\\*\\*");   //we found new fileName
            var matchx = px.matcher(line);
            if (matchx.matches())   //it means there is another fileToAdd
            {
                FullFile f = new FullFile();
//                ObsHackeeParser.FullFile f = new ObsHackeeParser.FullFile();
//                log("SUPER BOOM. " + i + "");
                //it means we are at file Title
//                String titlePath = line.substring(line.indexOf('`') + 1, line.lastIndexOf('`'));
//                f.filePath = titlePath;
                f.filePath = line.substring(line.indexOf('`') + 1, line.lastIndexOf('`'));

                for (int j = i + 2; j < fileContentSnippetLines.size(); j++) //get fileSnippetContent
                {
                    var ll = fileContentSnippetLines.get(j);
                    var ppx = Pattern.compile("```");   //search for ```, the end of code snippet
                    var mmm = ppx.matcher(ll);
                    if (mmm.matches())  //it means end of code Snippet found
                    {
//                        log("IT'S MATCH, ind: " + j);
//                        var fileContentLines = fileContentSnippetLines.subList(i + 2, j - 1);
                        var fileContentLines = fileContentSnippetLines.subList(i + 2, j);
//                        var fc = join(fileContentLines, '\n');
//                        fileContentLines.stream().collect(String.join("\n"))
                        var fc = String.join("\n", fileContentLines);
                        f.fileContent = fc;
                        fullFileList.add(f);

                        if (j + 1 >= fileContentSnippetLines.size())
                            return;

                        var linesForNextPart = fileContentSnippetLines.subList(j + 1, fileContentSnippetLines.size());
                        recAdd(fullFileList, linesForNextPart);
                        break outer;

//                        log("================================");
//                        log(f.filePath);
//                        log(f.fileContent);
//                        return;
                    }
                }
            }
        }
        log("FINALE!!");
//        return;



    }

    static void log(String msg)
    {
        try {
            Files.write(logPath, (msg + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("IDK, it won't show");
            throw new RuntimeException(e);
        }
    }


}



/*
			const count = (ss) => {
				//const re = /[a-z]{3}/g
//				const re = "\*\* Example \d+ start"
//				const re = "\\*\\* Example \\d+ start"
				//const re = /\*\* Example \d start\*\*/

//const re = /aa55/g
//				const re = /\*\* aaa/g
//				const re = /\*\*Example \d+ start\*\*/g

                        //const re = /exex/g
                        //const re = new RegEx/** Example \\d+ start/



//                        return ((ss || '').match(re) || []).length

//                        }

// */