package hu.bme.aut.recipebase.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class UnorderedListTransformation(private val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return filterRow(text, prefix)
    }

    private fun filterRow(input: AnnotatedString, prefix: String): TransformedText {
        if (input.text.isEmpty()) {
            val numberOffsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return offset
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return offset
                }
            }

            return TransformedText(input, numberOffsetTranslator)
        } else {
            val list = input.text.split("\n").toTypedArray()

            var out = ""
            list.forEach {
                if (list.size != 1 && out.isNotEmpty()) {
                    out += "\n"
                }
                out += prefix + it
            }
            val prefixOffset = prefix.length

            val numberOffsetTranslator = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return out.length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= prefixOffset - 1) return prefixOffset
                    return offset - list.size * prefixOffset
                }
            }

            return TransformedText(AnnotatedString(out), numberOffsetTranslator)
        }
    }
}