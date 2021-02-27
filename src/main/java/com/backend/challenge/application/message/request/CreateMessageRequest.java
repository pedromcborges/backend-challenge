package com.backend.challenge.application.message.request;

import com.backend.challenge.domain.Message;
import com.backend.challenge.domain.ChannelEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class CreateMessageRequest {

    @NotNull(message = "Field must not be null")
    public LocalDateTime sendDate;

    @NotNull(message = "Field must not be null")
    @NotBlank(message = "Field must not be blank")
    @Size(max = 128)
    public String destination;

    @Size(max = 128)
    public String message;

    @NotNull(message = "Field must not be null")
    public ChannelEnum channel;
}
