package com.samet.yazlab;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "text")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Text {

    @Id
    private ObjectId id;

    private String text;
    private String result;
    private long processTime;
}
