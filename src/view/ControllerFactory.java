package view;

import domain.model.ShopService;

public class ControllerFactory {

    private RequestHandler createHandler(String handlerName, ShopService model) {
        RequestHandler handler = null;
        try {
            Class<?> handlerClass = Class.forName("view."+ handlerName);
            Object handlerObject = handlerClass.newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setService(model);
        } catch (Exception e) {
            throw new RuntimeException("The requested page doesn’t exist");
        }
        return handler;
    }

    public RequestHandler getController(String key, ShopService model) {
        return createHandler(key, model);
    }

}
