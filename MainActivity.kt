package com.example.myapplication

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.data.remote.Organization
import com.example.myapplication.ui.OrganizationViewModel
import com.example.myapplication.ui.components.ParentOrgAdapter

class MainActivity : ComponentActivity() {

    private val viewModel: OrganizationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listView = findViewById<ListView>(R.id.listOrganizations)
        val editName = findViewById<EditText>(R.id.editOrgName)
        val editDesc = findViewById<EditText>(R.id.editOrgDescription)
        val autoParent = findViewById<AutoCompleteTextView>(R.id.autoParentOrg)
        var selectedParentId: Int? = null

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)


        viewModel.organizations.observe(this, Observer { orgs ->
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                orgs.map { org ->
                    if (!org.description.isNullOrEmpty())
                        "${org.id}. ${org.name} — ${org.description}"
                    else
                        "${org.id}. ${org.name}"
                }
            )
            listView.adapter = adapter

            // Autocomplete component
            val parentAdapter = ParentOrgAdapter(this, orgs)
            autoParent.setAdapter(parentAdapter)

            autoParent.setOnItemClickListener { _, _, position, _ ->
                val selectedOrg = parentAdapter.getItem(position)
                selectedParentId = selectedOrg?.id
                autoParent.setText(selectedOrg?.name ?: "", false)
            }

            autoParent.setOnClickListener {
                autoParent.showDropDown()
            }

            autoParent.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) autoParent.showDropDown()
            }
        })

        // Add
        btnAdd.setOnClickListener {
            val name = editName.text.toString().trim()
            val desc = editDesc.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val org = Organization(name = name, description = desc, parent_id = selectedParentId)
            viewModel.createOrganization(org)

            editName.text.clear()
            editDesc.text.clear()
            autoParent.setText("", false)
            selectedParentId = null
        }

        btnUpdate.setOnClickListener {
            val name = editName.text.toString().trim()
            val desc = editDesc.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val org = Organization(id = 1, name = name, description = desc, parent_id = selectedParentId)
            viewModel.updateOrganization(org)

            editName.text.clear()
            editDesc.text.clear()
            autoParent.setText("", false)
            selectedParentId = null
        }

        btnDelete.setOnClickListener {
            viewModel.deleteOrganization(1)
            Toast.makeText(this, "Deleted organization with ID 1", Toast.LENGTH_SHORT).show()
        }

        viewModel.loadOrganizations()
    }
}