package bilulo.com.androidtest03.helper

import android.widget.EditText
import java.util.regex.Pattern

class ValidationHelper {

    companion object {
        @JvmStatic
        fun isEmpty(editText: EditText): Boolean {
            return editText.text.toString().trim { it <= ' ' }.isEmpty()
        }

        @JvmStatic
        fun isValidCpf(cpf: String): Boolean {
            val CPF_PATTERN = Pattern.compile("^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}")
            return CPF_PATTERN.matcher(cpf).matches()
        }

        @JvmStatic
        fun isValidCep(cpf: String): Boolean {
            val CEP_PATTERN = Pattern.compile("^[0-9]{5}\\-[0-9]{3}")
            return CEP_PATTERN.matcher(cpf).matches()
        }
    }
}