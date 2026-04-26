package com.example.codeholiday.dto;

import com.example.codeholiday.model.Priority;
import com.example.codeholiday.model.Status;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class TodoDto {
    private Long id;

    @NotBlank(message = "Nội dung không được bỏ trống!")
    private String content;

    @FutureOrPresent(message = "Ngày đến hạn phải ở hiện tại hoặc tương lai!")
    @NotNull(message = "Ngày đến hạn không được null!")
    private LocalDate dueDate;

    @NotNull(message = "Trạng thái không được bỏ trống!")
    private Status status;

    @NotNull(message = "Độ ưu tiên không được bỏ trống!")
    private Priority priority;
}
