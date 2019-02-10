package mx.edu.ittepuc.tpdm_u1_practica1_oscar_ibaez_loreto;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText producto, descripcion;
    Spinner cantidad;
    Switch factura;
    Button confirmar, validar;
    RadioButton efectivo,credito;
    TextView estatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        producto = findViewById(R.id.producto);
        descripcion = findViewById(R.id.descripcion);
        cantidad = findViewById(R.id.cantidad);
        factura = findViewById(R.id.factura);
        confirmar = findViewById(R.id.confirmar);
        efectivo = findViewById(R.id.efectivo);
        credito = findViewById(R.id.credito);
        validar = findViewById(R.id.validar);
        estatus = findViewById(R.id.estatus);

        confirmar.setVisibility(View.INVISIBLE);
        estatus.setVisibility(View.INVISIBLE);

        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estatus.setVisibility(View.INVISIBLE);
                String productoText = producto.getText().toString();
                String descripcionText = descripcion.getText().toString();
                String cantidadProd = cantidad.getSelectedItem().toString();
                boolean facturaProd = factura.isChecked();
                boolean efectivoProd = efectivo.isChecked();
                String factura,efectivo,credito = "";

                String cadenaFinal;

                if(facturaProd){
                    factura = "Se recibio factura.";
                } else {
                    factura = "* OJO! NO recibio factura* ";
                }

                if(efectivoProd){
                    efectivo = "El pago se hizo en efectivo.";

                }else {
                    efectivo = "El pago se hizo con tarjeta de crédito.";
                }

                cadenaFinal =  "Producto: "+productoText+"\n"+
                               "Descripción: "+descripcionText+"\n"+
                                "Cantidad: "+cantidadProd+"\n"+
                                factura+"\n"+
                                efectivo
                        ;
                confirmar(cadenaFinal);
            }
        });

    }// onCreate

    private void validar(){
        estatus.setVisibility(View.INVISIBLE);
        if(producto.getText().toString().isEmpty() ||
                descripcion.getText().toString().isEmpty()){
            Toast.makeText(this,"Cápture ambos campos del producto!",Toast.LENGTH_LONG).show();
            confirmar.setVisibility(View.INVISIBLE);
        }else{
            Toast.makeText(this,"Datos correctos!",Toast.LENGTH_LONG).show();
            confirmar.setVisibility(View.VISIBLE);
        }
    }// validar


    private void confirmar(String cadena){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Resumen de inventario.")
                .setMessage("¿Son correctos los datos?\n\n"+cadena)
                .setPositiveButton("Correcto", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        estatus.setText("CONFIRMADO");
                        estatus.setTextColor(Color.GREEN);
                        estatus.setVisibility(View.VISIBLE);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        estatus.setText("CANCELADO");
                        estatus.setTextColor(Color.RED);
                        estatus.setVisibility(View.VISIBLE);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}// Class
