package com.example.BookMyShow.EntryDtos;

import javax.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class UserEntryDto {

    private String name;

    private String age;

    private String email;

    private int mobNo;

    private String address;
}
