/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 *
 * @author Payd3r
 */
class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    /**
     * Metodo per convertire una stringa in oggetto
     *
     * @param text Stringa da convertire
     * @return Oggetto convertito
     * @throws ParseException Eccezione in caso di errore di conversione
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    /**
     * Metodo per convertire un oggetto in stringa
     *
     * @param value Valore da convertire
     * @return Stringa convertita
     */
    @Override
    public String valueToString(Object value) {
        if (value != null) {
            if (value instanceof Calendar) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            } else if (value instanceof Date) {
                Date date = (Date) value;
                return dateFormatter.format(date);
            }
        }
        return "";
    }
}