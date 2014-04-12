package co.uk.shadowchild.platformer.impl;

import co.uk.shadowchild.platformer.event.Event;


/**
 * @author ShadowChild
 */
public interface IEventHandler<E extends Event> {

    public void handle(E e);
}
