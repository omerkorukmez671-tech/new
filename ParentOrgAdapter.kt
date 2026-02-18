package com.example.myapplication.ui.components

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.data.remote.Organization
import java.text.Normalizer

class ParentOrgAdapter(
    context: Context,
    private val items: List<Organization>
) : ArrayAdapter<Organization>(context, 0, items) {

    private val byId: Map<Int, Organization> = items.mapNotNull { org ->
        org.id?.let { it to org }
    }.toMap()

    override fun getCount(): Int = items.size
    override fun getItem(position: Int): Organization? = items[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)

        val title = view.findViewById<TextView>(android.R.id.text1)
        val subtitle = view.findViewById<TextView>(android.R.id.text2)

        val org = getItem(position)
        title.text = org?.name ?: ""

        // Üst birim hiyerarşisini gösterelim:
        val hierarchy = buildHierarchy(org?.parent_id)
        subtitle.text = hierarchy
        subtitle.setTextColor(Color.DKGRAY)
        subtitle.textSize = 12f

        return view
    }

    private fun buildHierarchy(parentId: Int?): String {
        if (parentId == null) return ""
        val parentOrg = items.find { it.id == parentId } ?: return ""
        val upper = buildHierarchy(parentOrg.parent_id)
        return if (upper.isNotEmpty()) "$upper > ${parentOrg.name}" else parentOrg.name
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Dropdown satırı da aynı görünsün
        return getView(position, convertView, parent)
    }

    /**
     * En üst > ... > En yakın üst formatında ata zinciri üretir.
     * Örn: Fakülte > Bölüm > Anabilim Dalı (org = Anabilim Dalı ise)
     */
    private fun buildAncestorChain(org: Organization): String {
        val chain = mutableListOf<String>()
        val seen = mutableSetOf<Int>() // Döngü koruması

        var currentParentId = org.parent_id
        while (currentParentId != null) {
            if (!seen.add(currentParentId)) break // döngü varsa kır
            val parent = byId[currentParentId] ?: break
            chain.add(parent.name)
            currentParentId = parent.parent_id
        }

        // Şu an chain: [En yakın üst, ... , En üst]  -> ters çevir
        chain.reverse()

        return if (chain.isEmpty()) "" else chain.joinToString(" > ")
    }

    override fun getFilter() = object : android.widget.Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            if (constraint.isNullOrBlank()) {
                results.values = items
                results.count = items.size
            } else {
                val normalizedConstraint = Normalizer.normalize(constraint.toString(), Normalizer.Form.NFD)
                    .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
                    .lowercase()

                val filtered = items.filter {
                    val normalizedName = Normalizer.normalize(it.name, Normalizer.Form.NFD)
                        .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
                        .lowercase()
                    val normalizedDescription = it.description?.let { desc ->
                        Normalizer.normalize(desc, Normalizer.Form.NFD)
                            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), "")
                            .lowercase()
                    }
                    normalizedName.contains(normalizedConstraint) ||
                            (normalizedDescription?.contains(normalizedConstraint) == true)
                }
                results.values = filtered
                results.count = filtered.size
            }
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            clear()
            @Suppress("UNCHECKED_CAST")
            addAll(results?.values as? List<Organization> ?: items)
            notifyDataSetChanged()
        }
    }
}