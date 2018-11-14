package com.dominandoandroid.example.hercules.e_moto.activity;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dominandoandroid.example.hercules.e_moto.R;
import com.dominandoandroid.example.hercules.e_moto.dao.ImagemDAO;
import com.dominandoandroid.example.hercules.e_moto.model.Imagem;
import com.dominandoandroid.example.hercules.e_moto.model.MotoTaxi;
import com.dominandoandroid.example.hercules.e_moto.utilitario.DbBitmapUtility;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MotoTaxi motoTaxi;
    private TextView txtNomeHeader, textTelefoneHeader;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private  NavigationView navigationView;
    private Dialog customDialog;

    private ImageView ivImagemPerfil;

    // a header nao eh inflada automaticamente, sendo assim
    // faz se necessario o uso dessa header
    private View header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.activity_home);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        // apareceu as 3 barrinhas para abrir o menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // eventos de click
        navigationView = findViewById(R.id.nav_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //you need to inflate the header view as it is not inflated automatically
        header = navigationView.getHeaderView(0);
        txtNomeHeader = header.findViewById(R.id.id_nome_header);
        textTelefoneHeader = header.findViewById(R.id.id_telefone_header);
        ivImagemPerfil = header.findViewById(R.id.header_id_foto_perfil);

        // recuperando dados passado da outra tela
        Bundle objetoEnviado = getIntent().getExtras();
        if (objetoEnviado != null){
            motoTaxi = (MotoTaxi) objetoEnviado.getSerializable("mototaxi");
            //adicionarInformacoesAoTextView();
            System.out.println("HOME " + motoTaxi.toString());

            autoCompletarHeader();

        } else{
            System.out.println("HOME NÃO RECUPEROU DADOS");
        }
    }

    // revolveu o problema de quando clicar nas 3 barrinhas
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_concluidas) {
            Toast.makeText(getApplicationContext(), "concluida", Toast.LENGTH_SHORT).show();
            //showCustomDialog();
        } else if (id == R.id.nav_canceladas) {
            Toast.makeText(getApplicationContext(), "canceladas", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_lista) {
            Toast.makeText(getApplicationContext(), "lista", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_configuracoes) {
            Toast.makeText(getApplicationContext(), "configuracoes", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_sair) {
            Toast.makeText(getApplicationContext(), "sair", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_seguranca) {
            Toast.makeText(getApplicationContext(), "Segurança", Toast.LENGTH_SHORT).show();
        }

        // fechar o navigation quando clicar
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    // quando clicar no botao de voltar
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }

    public void showCustomDialog(){
        customDialog = new Dialog(HomeActivity.this);
        customDialog.setContentView(R.layout.layout_customdialog);
        customDialog.setTitle("Aceitar Corrida");
        customDialog.show();
        //customDialog.

        //customDialog.cancel();
    }

    private void autoCompletarHeader(){
        txtNomeHeader.setText(motoTaxi.getDadosPessoais().getNome());
        textTelefoneHeader.setText(motoTaxi.getNumeroCelular());

        ImagemDAO imagemDAO = new ImagemDAO(HomeActivity.this);

        /*List<Imagem> l = imagemDAO.listar();
        for (int i=0;i<l.size();i++){
            System.out.println(l.get(i).toString());
            imagemDAO.getImage(1,"perfil");
            System.out.println("haha"+imagemDAO.getImage(1,"perfil"));
        }*/
        Imagem img = imagemDAO.getImage(1,"perfil");
        ivImagemPerfil.setImageBitmap(DbBitmapUtility.getImage(img.getDados()));
    }

    /**
     *  Verifica qual foi o cardview que foi clicado
     * */
    public void clickCardView(View view){
        switch (view.getId()){

            case R.id.home_cardview_pilotar:            // pilotar
                Toast.makeText(getApplicationContext(),"Pilotar",Toast.LENGTH_SHORT).show();
                break;

            case R.id.home_cardview_ganhos_diarios:     // ganhos diarios
                Toast.makeText(getApplicationContext(),"Ganhos do dia",Toast.LENGTH_SHORT).show();
                break;

            case R.id.home_cardview_depositar:          // depositar
                Toast.makeText(getApplicationContext(),"Depositar",Toast.LENGTH_SHORT).show();
                break;

            case R.id.home_cardview_lista_viagens:      // lista de viagens
                Toast.makeText(getApplicationContext(),"Lista de Viagens",Toast.LENGTH_SHORT).show();
                break;

            //case R.id.home_cardview_editar_usuario:     // editar usuario
            //    Toast.makeText(getApplicationContext(),"editar usuario",Toast.LENGTH_SHORT).show();

        }
    }

}
