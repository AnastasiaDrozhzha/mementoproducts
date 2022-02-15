package adrozhzha.mementoproducts.core.util;

public interface PageRequest {

    Long getOffset();

    Long getSize();

    Order getOrder();

    interface Order {
        enum Direction {
            ASC,
            DESC,
            UNSPECIFIED
        }

        default Direction getDirection() {
            return Direction.UNSPECIFIED;
        }
    }
}
