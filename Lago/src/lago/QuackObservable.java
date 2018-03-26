/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lago;

/**
 *
 * @author gsarria
 */
public interface QuackObservable {
    public void registrarObservador(Observador observador);
    public void notificarObservadores();
}
