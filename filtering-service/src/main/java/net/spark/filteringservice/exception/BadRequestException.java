package net.spark.filteringservice.exception;

public class BadRequestException extends RuntimeException {

  public BadRequestException(final String description) {
    super(description);
  }
}
