/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

/**
 *
 * @author Bilal
 */
public interface RequestCallBack {
        void success(String response);
    void error(Exception e);
}
