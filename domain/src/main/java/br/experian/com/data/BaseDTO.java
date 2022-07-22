package br.experian.com.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
    protected UUID uuid;
    protected LocalDateTime created;
    protected LocalDateTime modified;
    protected Boolean active;
    protected Boolean deleted;
}
