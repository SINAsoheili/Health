package presenter;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

import model.BloodGlucose;
import model.BloodPressure;
import model.Calories;
import model.DB_BloodGlucose;
import model.DB_BloodPressure;
import model.DB_Calories;
import model.DB_Illness;
import model.DB_MedicationSchedule;
import model.DB_Medicine;
import model.Illness;
import model.MedicationSchedule;
import model.Medicine;

public class Dashboard_Page_presenter implements Dashboard_page_contract.Dashboard_page_contract_presenter
{
    private Dashboard_page_contract.Dashboard_page_contract_view view_obj;
    private Context context;

    public Dashboard_Page_presenter(Fragment view_obj)
    {
        this.view_obj = (Dashboard_page_contract.Dashboard_page_contract_view) view_obj;
        this.context = view_obj.getContext();
    }

    public Dashboard_Page_presenter(AppCompatActivity view_obj)
    {
        this.view_obj = (Dashboard_page_contract.Dashboard_page_contract_view) view_obj;
        this.context = view_obj.getApplicationContext();
    }

    @Override
    public ArrayList<Medicine> get_all_medicine()
    {
        DB_Medicine db = new DB_Medicine(context);
        ArrayList<Medicine> all_item = db.get_all();
        return all_item;
    }

    @Override
    public ArrayList<Medicine> search_medicine(String query)
    {
        DB_Medicine db = new DB_Medicine(context);
        ArrayList<Medicine> items = db.search(query);
        return items;
    }

    @Override
    public ArrayList<Illness> get_all_illness()
    {
        DB_Illness db = new DB_Illness(context);
        ArrayList<Illness> all_item = db.get_all();
        return all_item;
    }

    @Override
    public ArrayList<Illness> search_illness(String query)
    {
        DB_Illness db = new DB_Illness(context);
        ArrayList<Illness> items = db.search(query);
        return items;
    }

    @Override
    public ArrayList<MedicationSchedule> get_all_Medication_Schedule()
    {
        DB_MedicationSchedule db = new DB_MedicationSchedule(context);
        ArrayList<MedicationSchedule> all_items = db.get_all();
        return all_items;
    }

    @Override
    public boolean insert_new_medicationSchedule(MedicationSchedule m)
    {
        DB_MedicationSchedule db = new DB_MedicationSchedule(context);
        Long id = db.insert(m);
        if(id != -1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean update_medication_Schedule(MedicationSchedule mold , MedicationSchedule mnew)
    {
        DB_MedicationSchedule db = new DB_MedicationSchedule(context);

        int count = db.update(mold , mnew);

        if(count == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean delete_medication_Schedule(MedicationSchedule m)
    {
        DB_MedicationSchedule db = new DB_MedicationSchedule(context);

        int count = db.delete(m);

        if(count == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public ArrayList<BloodGlucose> get_all_blood_glucose()
    {
        DB_BloodGlucose db = new DB_BloodGlucose(context);
        ArrayList<BloodGlucose> all_item = db.get_all();
        return all_item;
    }

    @Override
    public ArrayList<BloodPressure> get_all_blood_pressure()
    {
        DB_BloodPressure db = new DB_BloodPressure(context);
        ArrayList<BloodPressure> all_item = db.get_all();
        return all_item;
    }

    @Override
    public ArrayList<Calories> get_all_calories()
    {
        DB_Calories db = new DB_Calories(context);
        ArrayList<Calories> all_item = db.get_all();
        return all_item;
    }

    @Override
    public ArrayList<Calories> search_calories(String name)
    {
        DB_Calories db = new DB_Calories(context);
        ArrayList<Calories> all_item = db.search(name);
        return all_item;
    }

    @Override
    public Calories search_calories(int id)
    {
        DB_Calories db = new DB_Calories(context);
        Calories item = db.search(id);
        return item;
    }

}
