    package com.example.demo.mongoModel;

    import org.springframework.data.mongodb.core.mapping.Document;

    import jakarta.persistence.Id;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Document(collection = "comments")
    public class Comment {
        @Id
        private String id;
        private String user;
        private String content;
    }
