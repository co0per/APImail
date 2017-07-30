package tp.Controller;

import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import tp.Request.MensajeRequest;
import tp.Wrapper.MensajeWrapper;
import tp.Wrapper.MensajeWrapperLeer;
import tp.Services.MensajeService;
import tp.Model.Mensaje;
import tp.Converter.MensajeConverter;

@RestController
public class MensajeController
{
    @Autowired
    MensajeService mensajeService;

    @Autowired
    MensajeConverter convertidor;

    @RequestMapping(value = "api/enviar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity enviarMensaje (@RequestBody Mensaje mensaje){
        try{
            mensajeService.enviarMensajeService(mensaje);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "api/mensajes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ArrayList<MensajeWrapper>> getMensajesEnviados(@RequestParam(value = "remitente") String remi){
        try{
            ArrayList<Mensaje> lista_enviados = mensajeService.getMensajesService(remi);
            if (lista_enviados.isEmpty()){
                return new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<ArrayList<MensajeWrapper>>(this.convertirLista(lista_enviados), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "api/mensaje/inbox", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ArrayList<MensajeWrapper>> getInbox (@RequestParam("recipiente") String reci){
        try{
            ArrayList<Mensaje> mensajes = mensajeService.getInbox(reci);
            if (mensajes.isEmpty()){
                return new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<ArrayList<MensajeWrapper>>(this.convertirLista(mensajes), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/mensajes/eliminados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ArrayList<MensajeWrapper>> getMensajesEliminados (@RequestParam(value = "recipiente") String reci_mail){
        try{
            ArrayList<Mensaje> mensajes = mensajeService.getMensajesBorradosService(reci_mail);
            if (mensajes.isEmpty()){
                return new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.NO_CONTENT);
            }
            return  new ResponseEntity<ArrayList<MensajeWrapper>>(this.convertirLista(mensajes) ,HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<ArrayList<MensajeWrapper>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/mensaje", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity eliminarMensaje (@RequestParam("id_mensaje") int id){
        try{
            mensajeService.eliminarMensajeService(id);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return  new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ArrayList<MensajeWrapper> convertirLista(ArrayList<Mensaje> listMensajes) {
        ArrayList<MensajeWrapper> mensajeWrapperList = new ArrayList<MensajeWrapper>();
        for(Mensaje mensaje : listMensajes){
            mensajeWrapperList.add(convertidor.convert(mensaje));
        }
        return mensajeWrapperList;
    }

    private ArrayList<MensajeWrapperLeer> convertirListaLeer(ArrayList<Mensaje> listMensajes) {
        ArrayList<MensajeWrapperLeer> mensajeWrapperList = new ArrayList<MensajeWrapperLeer>();
        for(Mensaje mensaje : listMensajes){
            mensajeWrapperList.add(convertidor.convertLeer(mensaje));
        }
        return mensajeWrapperList;
    }

    @RequestMapping(value = "api/mensaje/leer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ArrayList<MensajeWrapperLeer>> Leer (@RequestParam("id_mensaje") int id_mensaje){
        try{
            ArrayList<Mensaje> mensaje = mensajeService.leerMensaje(id_mensaje);
            if (mensaje.isEmpty()){
                return new ResponseEntity<ArrayList<MensajeWrapperLeer>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<ArrayList<MensajeWrapperLeer>>(this.convertirListaLeer(mensaje), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<ArrayList<MensajeWrapperLeer>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}