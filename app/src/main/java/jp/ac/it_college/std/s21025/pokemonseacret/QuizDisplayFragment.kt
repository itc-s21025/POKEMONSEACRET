package jp.ac.it_college.std.s21025.pokemonseacret

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import jp.ac.it_college.std.s21025.pokemonseacret.databinding.FragmentQuizDisplayBinding
import jp.ac.it_college.std.s21025.pokemonsecret.PokemonResponce
import jp.ac.it_college.std.s21025.pokemonsecret.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val BASE_URL = "https://pokeapi.co/api/v2/"

class QuizDisplayFragment : Fragment() {
    private var _binding: FragmentQuizDisplayBinding? = null
    private val binding get() = _binding!!
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val args2: QuizDisplayFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizDisplayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var count = 0
        binding.imageView.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
        var tmp: Int = randomNum(args2.a)
        var clicked = false
        val qCount = args2.qCount
        var score = args2.score
        binding.textView2.text = getString(R.string.count, qCount)
        val list1 = listOf(
            binding.answer1,
            binding.answer2,
            binding.answer3,
            binding.answer4
        ).shuffled()

        var num1 = randomNum(args2.a)
        var num2 = randomNum(args2.a)
        var num3 = randomNum(args2.a)

        lifecycleScope.launch {
            val info = getPokeInfo(num1)
            val info2 = getPokeInfo(num2)
            val info3 = getPokeInfo(num3)
            val info4 = getPokeInfo(tmp)
            list1[0].text= getString(R.string.answer1, info4.name)
            list1[1].text= getString(R.string.answer1, info.name)
            list1[2].text= getString(R.string.answer1, info2.name)
            list1[3].text= getString(R.string.answer1, info3.name)
            PokeInfoImg(tmp)
        }

        tmp = randomNum(args2.a)
        count += 1

        class ClickListener(val right: Boolean) : View.OnClickListener{
            override fun onClick(v: View) {
                clicked = true
                if (right) {
                    Toast.makeText(context, getString(R.string.ok, ),Toast.LENGTH_SHORT).show()
                    score++
                } else {
                    Toast.makeText(context, getString(R.string.miss, ), Toast.LENGTH_SHORT).show()
                }

                if (qCount < 10) {
                    Navigation.findNavController(view).navigate(
                        QuizDisplayFragmentDirections.actionQuizDisplayFragmentSelf2(
                            args2.a
                        ).apply {
                            setQCount(args2.qCount + 1)
                            setScore(score)
                        }
                    )

                } else {
                    Navigation.findNavController(view).navigate(
                        QuizDisplayFragmentDirections.actionQuizDisplayFragmentToResultDisplayFragment(score)
                    )
                }
            }

        }
        list1[0].setOnClickListener (ClickListener(true))
        list1[1].setOnClickListener (ClickListener(false))
        list1[2].setOnClickListener (ClickListener(false))
        list1[3].setOnClickListener (ClickListener(false))

        val h = Handler(Looper.getMainLooper())
        h.postDelayed(object : Runnable {
            var counter = 10
            override fun run() {
                if (clicked) {
                    return
                }
                if (counter <= 0) {
                    ClickListener(false).onClick(view)
                    return
                }
                binding.timer.text = getString(R.string.timer, counter)
                counter--
                h.postDelayed(this, 1000L)
            }
        }, 0L)
    }



    @UiThread
    private fun PokeInfoImg(random: Int) {
        lifecycleScope.launch {
            val info = getPokeInfo(random)
            val url = info.sprites.other.officialArtwork.frontDefault
            Picasso.get().load(url).into(binding.imageView)
        }
    }

    private fun Pokequiz(args: Int, args2: Int, args3: Int, tmp: Int) {
        lifecycleScope.launch {
            val info = getPokeInfo(args)
            val info2 = getPokeInfo(args2)
            val info3 = getPokeInfo(args3)
            val info4 = getPokeInfo(tmp)
            val Array = mutableListOf(info.name, info2.name, info3.name, info4.name)
            Array.shuffle()
            binding.answer1.text = getString(R.string.answer1, Array[0])
            binding.answer2.text = getString(R.string.answer1, Array[1])
            binding.answer3.text = getString(R.string.answer1, Array[2])
            binding.answer4.text = getString(R.string.answer1, Array[3])
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    @WorkerThread
    private suspend fun getPokeInfo(random: Int): PokemonResponce {
        return withContext(Dispatchers.IO) {
            val retrofit = Retrofit.Builder().apply {
                baseUrl(BASE_URL)
                addConverterFactory(MoshiConverterFactory.create(moshi))
            }.build()
            val service: PokemonService = retrofit.create(PokemonService::class.java)
            service.fetchPoke(random).execute().body()
                ?: throw IllegalStateException("データがないです")
        }
    }

    private fun randomNum(num: Int): Int {
        val args = when (num) {
            //0 -> {
                //(1..151).shuffled().first()
            //}
            1 -> {
                (1..151).shuffled().first()
            }
            2 -> {
                (152..251).shuffled().first()
            }
            3 -> {
                (252..386).shuffled().first()
            }
            4 -> {
                (387..493).shuffled().first()
            }
            5 -> {
                (494..649).shuffled().first()
            }
            6 -> {
                (650..721).shuffled().first()
            }
            7 -> {
                (722..809).shuffled().first()
            }
            8 -> {
                (810..905).shuffled().first()
            }
            else -> {
                (905..950).shuffled().first()
            }
        }
        return args
    }


}










