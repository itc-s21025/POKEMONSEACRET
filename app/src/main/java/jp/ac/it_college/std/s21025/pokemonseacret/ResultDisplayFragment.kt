package jp.ac.it_college.std.s21025.pokemonseacret

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import jp.ac.it_college.std.s21025.pokemonseacret.databinding.FragmentResultDisplayBinding

class ResultDisplayFragment : Fragment() {
    private var _binding: FragmentResultDisplayBinding? = null
    private val binding get() = _binding!!
    private val args: ResultDisplayFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        _binding = FragmentResultDisplayBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            Navigation.findNavController(it).navigate(
                ResultDisplayFragmentDirections.actionResultDisplayFragmentToQuizSelectDisplayFragment()
            )
        }
        val score = args.score
        binding.textView4.text = getString(R.string.enpty, score)

        return binding.root
    }
}