package com.example.transfers.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.transfers.R;
import com.example.transfers.businessObject.DataBase;
import com.example.transfers.dataObject.Const;
import com.example.transfers.dataObject.User;

import java.util.Vector;

public class AddUserActivity extends Activity implements View.OnClickListener {

    Spinner spTipoDocumento,spTipoContribuyente, spTipoOtro;
    EditText txtDireccion, txtTelefono, txtCorreo, txtApellidoDos, txtApellidoUno, txtNombre, txtRazonSocial, txtNDocumento, txtTelex, txtApartadoAereo,
    txtFax, txtDigitoVerificacion;
    Button btnFinalizar,btn_ver_dv;
    LinearLayout lyBtnVerificarDigito, lyDigitoVerificacion;

    Vector<String> listaTipoDocumento;

    String tipoDocumento="";

    private DataBase db;

    User u;

    User user;

    boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_activity);
        db = new DataBase(AddUserActivity.this);
        u = new User();
        getExtras();
        cargarVista();

    }
    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            user = new User();
            user = (User) bundle.getSerializable(Const.USER_SEL);
            isEdit = (boolean) bundle.getSerializable(Const.USER_EDI);
        }
    }

    private void cargarVista() {
        txtNDocumento = (EditText) findViewById(R.id.txtNDocumento);
        txtRazonSocial = (EditText) findViewById(R.id.txtRazonSocial);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtApellidoUno = (EditText)  findViewById(R.id.txtApellidoUno);
        txtApellidoDos = (EditText)  findViewById(R.id.txtApellidoDos);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtDireccion = (EditText) findViewById(R.id.txtDireccion);
        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtFax = (EditText) findViewById(R.id.txtFax);
        txtApartadoAereo = (EditText) findViewById(R.id.txtApartadoAereo);
        txtTelex = (EditText) findViewById(R.id.txtTelex);
        txtDigitoVerificacion = (EditText) findViewById(R.id.txtDigitoVerificacion);
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);
        btnFinalizar.setOnClickListener(this);
        lyDigitoVerificacion = (LinearLayout) findViewById(R.id.lyDigitoVerificacion);
        lyBtnVerificarDigito = (LinearLayout) findViewById(R.id.lyBtnVerificarDigito);
        btn_ver_dv = (Button) findViewById(R.id.btn_ver_dv);
        btn_ver_dv.setOnClickListener(this);
        cargarSpinners();

        if(isEdit){
            txtNDocumento.setText(user.getId() +"");
            user = db.cargarUsuario(user.getId()+"");
            //u.setCodEmpresa("");
            txtRazonSocial.setText(user.getNombrePersonaJuridica());
            int pos = 0;
            if(user.getTipoNit().equals(" NIT")) pos =1;
            spTipoDocumento.setSelection(pos);
            txtDireccion.setText(user.getDireccion());
            txtTelefono.setText(user.getTelefono());
            txtApartadoAereo.setText(user.getApartadoAereo());
            //u.setEstado("");
            txtNombre.setText(user.getPrimerNombre());
            txtApellidoUno.setText(user.getPriemrApellido());
            txtApellidoDos.setText(user.getSegundoApellido());
            txtCorreo.setText(user.getCorreo());
            if(pos == 1)  txtDigitoVerificacion.setText(user.getDigitoVerificacion());
        }

    }
    private void cargarSpinners() {
        cargarSpTipoDocumento();
    }
    private void cargarSpTipoDocumento() {

        spTipoDocumento = (Spinner) findViewById(R.id.spTipoDocumento);
        listaTipoDocumento = new Vector<String>();
        listaTipoDocumento.add( "CEDULA" );
        listaTipoDocumento.add( "NIT" );
        spTipoDocumento.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listaTipoDocumento));
        spTipoDocumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                tipoDocumento = (String) adapterView.getItemAtPosition(position);
                validarDigito((String) adapterView.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // vacio

            }
        });

    }

    private void validarDigito(String itemAtPosition) {
        String numId= txtNDocumento.getText().toString().trim();
        int impar =0;
        int resulImpar =0;
        int par = 0;
        int resulSum =0;
        int res = 0;
        int multiplo = 10;
        int number = 0;
        if(itemAtPosition.equals("NIT")){
            lyBtnVerificarDigito.setVisibility(View.VISIBLE);
            lyDigitoVerificacion.setVisibility(View.VISIBLE);
        }
        char[] l =numId.toCharArray();
        if(l.length>9){
            Toast.makeText(this, (String) "El numero de documento supera la cantidad de digitos ", Toast.LENGTH_SHORT).show();
        }else{
            for (int i = 0; i < l.length; i++) {
                if(i%2==0) {
                    impar = impar + Character.getNumericValue(l[i]);
                }
                if(i%2!=0) {
                    par = par + Character.getNumericValue(l[i]);
                }
            }
            resulImpar = impar*3;
            resulSum = par +resulImpar;
            res= resulSum % multiplo;
            if(res == 0) {
                txtDigitoVerificacion.setText(res+"");
            }else{
                number = multiplo- res;
                txtDigitoVerificacion.setText(number+"");
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(v==btnFinalizar){
            if(isEdit){
                editar();
            }else{
                guardar();
            }

        }else if(v == btn_ver_dv){
            validarDigito(tipoDocumento);
        }

    }

    private void editar() {
        u.setId(Integer.parseInt(txtNDocumento.getText().toString()));
        u.setCodEmpresa("");
        u.setNombrePersonaJuridica(txtRazonSocial.getText().toString().trim());
        u.setTipoId(tipoDocumento);
        u.setDireccion(txtDireccion.getText().toString().trim());
        u.setTelefono(txtTelefono.getText().toString().trim());
        u.setApartadoAereo(txtApartadoAereo.getText().toString().trim());
        u.setEstado("");
        u.setPrimerNombre(txtNombre.getText().toString().trim());
        u.setPriemrApellido(txtApellidoUno.getText().toString().trim());
        u.setSegundoApellido(txtApellidoDos.getText().toString().trim());
        u.setCorreo(txtCorreo.getText().toString().trim());
        u.setDigitoVerificacion(txtDigitoVerificacion.getText().toString().trim());
        u.setTipoNit("");
        u.setTipoContribuyente("");
        u.setTipoTercero("");
        u.setFecha("");
        db.editarUsuario(u, user);

        finish();
    }

    private void guardar() {
        u.setId(Integer.parseInt(txtNDocumento.getText().toString()));
        u.setCodEmpresa("");
        u.setNombrePersonaJuridica(txtRazonSocial.getText().toString().trim());
        u.setTipoId(tipoDocumento);
        u.setDireccion(txtDireccion.getText().toString().trim());
        u.setTelefono(txtTelefono.getText().toString().trim());
        u.setApartadoAereo(txtApartadoAereo.getText().toString().trim());
        u.setEstado("");
        u.setPrimerNombre(txtNombre.getText().toString().trim());
        u.setPriemrApellido(txtApellidoUno.getText().toString().trim());
        u.setSegundoApellido(txtApellidoDos.getText().toString().trim());
        u.setCorreo(txtCorreo.getText().toString().trim());
        u.setDigitoVerificacion(txtDigitoVerificacion.getText().toString().trim());
        u.setTipoNit("");
        u.setTipoContribuyente("");
        u.setTipoTercero("");
        u.setFecha("");

        db.addNits(u);

        finish();
    }
}
