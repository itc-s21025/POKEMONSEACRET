package jp.ac.it_college.std.s21025.pokemonseacret

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import jp.ac.it_college.std.s21025.pokemonseacret.databinding.FragmentQuizSelectDisplayBinding


class QuizSelectDisplayFragment : Fragment() {
    private var _binding: FragmentQuizSelectDisplayBinding? = null
    private val binding get() = _binding!!


    var name = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizSelectDisplayBinding.inflate(inflater, container, false)

        binding.Dai1.setOnClickListener {
            Navigation.findNavController(it).navigate(
                QuizSelectDisplayFragmentDirections.actionQuizSelectDisplayFragmentToQuizDisplayFragment(1).apply {

                }
            )
        }
        binding.Dai2.setOnClickListener {
            Navigation.findNavController(it).navigate(
                QuizSelectDisplayFragmentDirections.actionQuizSelectDisplayFragmentToQuizDisplayFragment(2).apply {

                }
            )
        }
        binding.Dai3.setOnClickListener {

            Navigation.findNavController(it).navigate(
                QuizSelectDisplayFragmentDirections.actionQuizSelectDisplayFragmentToQuizDisplayFragment(3).apply {

                }
            )
        }
        binding.Dai4.setOnClickListener {
            Navigation.findNavController(it).navigate(
                QuizSelectDisplayFragmentDirections.actionQuizSelectDisplayFragmentToQuizDisplayFragment(4
                    ).apply {

                }
            )
        }

        binding.Dai5.setOnClickListener {
            Navigation.findNavController(it).navigate(
                QuizSelectDisplayFragmentDirections.actionQuizSelectDisplayFragmentToQuizDisplayFragment(5).apply {

                }
            )
        }
        binding.Dai6.setOnClickListener {
            Navigation.findNavController(it).navigate(
                QuizSelectDisplayFragmentDirections.actionQuizSelectDisplayFragmentToQuizDisplayFragment(6).apply {

                }
            )
        }

        binding.Dai7.setOnClickListener {
            Navigation.findNavController(it).navigate(
                QuizSelectDisplayFragmentDirections.actionQuizSelectDisplayFragmentToQuizDisplayFragment(7).apply {

                }
            )
        }
        binding.Dai8.setOnClickListener {
            Navigation.findNavController(it).navigate(
                QuizSelectDisplayFragmentDirections.actionQuizSelectDisplayFragmentToQuizDisplayFragment(8).apply {

                }
            )
        }

        binding.Dai9.setOnClickListener {
            Navigation.findNavController(it).navigate(
                QuizSelectDisplayFragmentDirections.actionQuizSelectDisplayFragmentToQuizDisplayFragment(9).apply {

                }
            )
        }
        return binding.root
    }

}