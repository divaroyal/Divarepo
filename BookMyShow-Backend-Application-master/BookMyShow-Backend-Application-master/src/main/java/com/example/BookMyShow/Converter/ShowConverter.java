package com.example.BookMyShow.Converter;

import com.example.BookMyShow.EntryDtos.ShowEntryDto;
import com.example.BookMyShow.Models.Show;

public class ShowConverter {

    public static Show convertDtoToEntity(ShowEntryDto showEntryDto){
        Show show=Show.builder().showDate(showEntryDto.getLocalDate()).showTime(showEntryDto.getLocalTime())
                .showType(showEntryDto.getShowType()).build();
        return show;
    }
}
