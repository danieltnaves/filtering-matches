package net.spark.filteringservice.exception;

public class NotFoundException extends RuntimeException {

  public NotFoundException(final String description) {
    super(description);
  }
}
