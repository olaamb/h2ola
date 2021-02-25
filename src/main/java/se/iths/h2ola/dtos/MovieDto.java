package se.iths.h2ola.dtos;

public class MovieDto {
    private long id;
    private String title;
    private String genre;

    public MovieDto(long id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
