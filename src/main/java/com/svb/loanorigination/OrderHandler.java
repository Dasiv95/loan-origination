package com.svb.loanorigination;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class OrderHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent, String> {
}
