package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CategoryManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Category;
import ba.unsa.etf.rpr.domain.Singer;
import ba.unsa.etf.rpr.domain.Song;
import ba.unsa.etf.rpr.exception.JukeBoxException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.List;

public class App {

    private static final Option addCategory = new Option("ac","add-category",false, "Adding new category to JukeBox database");
    private static final Option getAllCategories = new Option("c","get-all-categoreies",false, "Getting all categories from JukeBox database");
    private static final Option addSinger = new Option("as","add-singer",false, "Adding new singer to JukeBox database");
    private static final Option getAllSingers = new Option("s","get-all-singers",false, "Getting all singers from JukeBox database");
    private static final Option addSong = new Option("aso","add-song",false, "Adding new song to JukeBox database");
    private static final Option getAllSongs = new Option("so","get-all-songs",false, "Getting all categories from JukeBox database");
    private static final Option deleteSong = new Option("dso","delete-song",false, "Delete song from JukeBox database");


    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar juke-box.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions() {
        Options options = new Options();
        options.addOption(addCategory);
        options.addOption(getAllCategories);
        options.addOption(addSinger);
        options.addOption(getAllSingers);
        options.addOption(addSong);
        options.addOption(getAllSongs);
        options.addOption(deleteSong);

        return options;
    }

    public static void main( String[] args ) throws ParseException {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine commandLine = commandLineParser.parse(options, args);

        if (commandLine.hasOption(getAllCategories.getOpt()) || commandLine.hasOption(getAllCategories.getLongOpt())) {
            try {
                getAllCategories();
            } catch (JukeBoxException e) {
                System.out.println("Error getting categories: " + e.getMessage());
            }
        } else if (commandLine.hasOption(addCategory.getOpt()) || commandLine.hasOption(addCategory.getLongOpt())) {
            List<String> arguments = commandLine.getArgList();
            String categoryName = arguments.get(0);
            try {
                addCategory(categoryName);
            } catch (JukeBoxException e) {
                System.out.println("Error adding category "+ categoryName + " : " + e.getMessage());
            }
        } else if (commandLine.hasOption(getAllSingers.getLongOpt()) || commandLine.hasOption(getAllSingers.getLongOpt())) {
            try {
                getAllSingers();
            } catch (JukeBoxException e) {
                System.out.println("Error getting singers: " + e.getMessage());
            }
        } else if (commandLine.hasOption(addSinger.getOpt()) || commandLine.hasOption(addSinger.getLongOpt())) {
            List<String> arguments = commandLine.getArgList();
            String singerName = arguments.get(0);
            String categoryName = arguments.get(1);
            try {
                addSinger(singerName, categoryName);
            } catch (JukeBoxException e) {
                System.out.println("Error adding singer "+ singerName + " : " + e.getMessage());
            }
        } else if (commandLine.hasOption(getAllSongs.getOpt()) || commandLine.hasOption(getAllSongs.getLongOpt())) {
            try {
                getAllSongs();
            } catch (JukeBoxException e) {
                System.out.println("Error getting songs: " + e.getMessage());
            }
        } else if (commandLine.hasOption(addSong.getOpt()) || commandLine.hasOption(addSong.getLongOpt())) {
            List<String> arguments = commandLine.getArgList();
            String songName = arguments.get(0);
            String link = arguments.get(1);
            String singerName = arguments.get(2);
            try {
                addSong(songName, link, singerName);
            } catch (JukeBoxException e) {
                System.out.println("Error adding song " + songName + " : " + e.getMessage());
            }
        } else if (commandLine.hasOption(deleteSong.getOpt()) || commandLine.hasOption(deleteSong.getLongOpt())) {
            List<String> arguments = commandLine.getArgList();
            String songName = arguments.get(0);
            try {
                deleteSong(songName);
            } catch (JukeBoxException e) {
                System.out.println("Error deleting song " + songName + ": " + e.getMessage());
            }
        } else {
            printFormattedOptions(options);
            System.exit(-1);
        }
    }

    private static void addCategory(String categoryName) throws JukeBoxException {
        CategoryManager categoryManager = new CategoryManager();
        Category category = new Category();
        category.setName(categoryName);
        category = categoryManager.add(category);
        System.out.println("Successfully added category: " + category.toString());
    }

    public static void getAllCategories() throws JukeBoxException {
        List<Category> categories = DaoFactory.categoryDao().getAll();
        System.out.println("Categories:");
        for (Category category : categories) {
            System.out.println(category);
        }
    }

    public static void addSinger(String singerName, String categoryName) throws JukeBoxException {
        Singer singer = new Singer();
        singer.setName(singerName);

        List<Category> categories = DaoFactory.categoryDao().getAll();
        Category category = null;
        for (Category c : categories) {
            if (c.getName().equals(categoryName)) {
                category = c;
            }
        }
        singer.setCategory(category);
        DaoFactory.singerDao().add(singer);
    }

    public static void getAllSingers() throws JukeBoxException {
        List<Singer> singers = DaoFactory.singerDao().getAll();
        System.out.println("Singers:");
        for (Singer singer : singers) {
            System.out.println(singer);
        }
    }

    public static void getAllSongs() throws JukeBoxException {
        List<Song> songs = DaoFactory.songDao().getAll();
        System.out.println("Songs:");
        for (Song song : songs) {
            System.out.println(song);
        }
    }

    public static void addSong(String songName, String link, String singerName) throws JukeBoxException {
        Song song = new Song();
        song.setName(songName);
        song.setLink(link);
        List<Singer> singers = DaoFactory.singerDao().getAll();
        Singer singer = null;
        for (Singer s : singers) {
            if (s.getName().equals(singerName)) {
                singer = s;
            }
        }
        song.setSinger(singer);
        DaoFactory.songDao().add(song);
    }

    public static void deleteSong(String songName) throws JukeBoxException {
        List<Song> songs = DaoFactory.songDao().getAll();
        for (Song song : songs) {
            if (song.getName().equals(songName)) {
                DaoFactory.songDao().delete(song.getId());
            }
        }
    }

}
